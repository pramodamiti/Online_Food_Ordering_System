import { Component, h, State } from '@stencil/core';

@Component({
  tag: 'home-items',
  styleUrl: 'home-items.css',
  shadow: true,
})
export class HomeItems {
  @State() menuItems: any[] = [];

  async componentWillLoad() {
    try {
      let token = localStorage.getItem('jwt');
      const response = await fetch('http://localhost:8080/menu', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      console.log('Fetch token:', token);
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      this.menuItems = await response.json();
    } catch (error) {
      console.error('Error fetching menu items:', error);
    }
  }

  handleAddToCart(itemId: number, price: number, quantity: number = 1) {
    const token = localStorage.getItem('jwt');
    const userId = localStorage.getItem('userId');

    // if not logged in → send to login
    if (!token || !userId) {
      window.location.href = '/login'; // adjust route to your login page
      return;
    }

    fetch(`http://localhost:8080/users/${userId}/cart/items`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
      },
      body: JSON.stringify({
        quantity: quantity,
        totalPrice: price,
        cart: { cartId: 11 },
        menuItem: { itemId: itemId },
      }),
    })
      .then(res => {
        console.log('UserId:', userId, 'Token:', token);

        if (!res.ok) throw new Error(`Failed to add to cart: ${res.status}`);
        console.log(`Item ${itemId} added to cart`);
      })
      .catch(err => console.error(err));
  }

  handleOrderNow(itemId: number) {
    const token = localStorage.getItem('jwt');
    const userId = localStorage.getItem('userId');

    if (!token || !userId) {
      window.location.href = '/login';
      return;
    }

    fetch(`http://localhost:8080/payment`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
      },
      body: JSON.stringify({ userId, itemId }), // depends on your backend contract
    })
      .then(res => {
        if (!res.ok) throw new Error(`Payment failed: ${res.status}`);
        console.log(`Payment initiated for item ${itemId}`);
      })
      .catch(err => console.error(err));
  }
  render() {
    return (
      <div class="menu-container">
        {this.menuItems.length === 0 ? (
          <p>No items found. Maybe feed your backend some data first?</p>
        ) : (
          this.menuItems.map(item => (
            <div class="menu-item">
              <h3>{item.name}</h3>
              <p>{item.description}</p>
              <p>id{item.itemId}</p>
              <p>₹{item.price}</p>
              <div class="buttons">
                <button onClick={() => this.handleAddToCart(item.itemId, item.price, item.quantity)}>Add</button>
              </div>
            </div>
          ))
        )}
      </div>
    );
  }
}
