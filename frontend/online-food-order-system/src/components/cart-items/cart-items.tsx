import { Component, h, State } from '@stencil/core';

@Component({
  tag: 'cart-items',
  styleUrl: 'cart-items.css',
  shadow: true,
})
export class CartItems {
  @State() cartItems: any[] = [];
  @State() loading = true;

  async componentWillLoad() {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('jwt');

    if (!userId || !token) {
      window.location.href = '/login';
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/users/${userId}/cart`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (!response.ok) throw new Error(`HTTP error ${response.status}`);
      console.log('Response Status:', response.status);
      console.log('Response Headers:', response.headers);

      const data = await response.json();
      console.log('Cart Data:', data);
      this.cartItems = data.cartItems || [];
    } catch (error) {
      console.error('Failed to load cart items:', error);
    } finally {
      this.loading = false;
    }
  }
  async updateQuantity(cartItemId: number, newQty: number) {
    const token = localStorage.getItem('jwt');
    const userId = localStorage.getItem('userId');
    if (newQty < 1) this.deleteItem(cartItemId);

    try {
      const response = await fetch(`http://localhost:8080/users/${userId}/cart/items/${cartItemId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify({ quantity: newQty }),
      });

      if (!response.ok) {
        console.error('Failed to update quantity');
        return;
      }

      this.componentWillLoad();
    } catch (err) {
      console.error('Error updating quantity:', err);
    }
  }

  deleteItem(cartItemId: number) {
    const token = localStorage.getItem('jwt');
    const userId = localStorage.getItem('userId');

    fetch(`http://localhost:8080/users/${userId}/cart/items/${cartItemId}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
      },
    })
      .then(res => {
        console.log('UserId:', userId, 'Token:', token);

        if (!res.ok) {
          throw new Error(`Failed to delete from cart: ${res.status}`);
        }
        this.cartItems = this.cartItems.filter(item => item.cartItemId !== cartItemId);
      })
      .catch(err => console.error(err));
  }

  render() {
    if (this.loading) {
      return <p>Loading your cart...</p>;
    }

    if (this.cartItems.length === 0) {
      return (
        <div class="main">
          <div class="navbar">
            <app-navbar></app-navbar>
          </div>
          <div class="cart">
            <h1>Your Cart Items</h1>
            <p>Your cart is empty.</p>
          </div>
        </div>
      );
    }

    return (
      <div class="main">
        <div class="navbar">
          <app-navbar></app-navbar>
        </div>
        <div class="cart">
          <h1>Your Cart Items</h1>
          <ul>
            {this.cartItems.map(item => (
              <li class="cart-item">
                <h2 class="item-name">{item.menuItem.name}</h2>

                <div class="item-details">
                  <span>Qty: {item.quantity}</span>
                  <span>Price: ₹{item.menuItem.price}</span>
                  <span>Total: ₹{item.totalPrice}</span>
                </div>

                <div class="actions">
                  <button class="qty-btn" onClick={() => this.updateQuantity(item.cartItemId, item.quantity - 1)}>
                    -
                  </button>
                  <button class="qty-btn" onClick={() => this.updateQuantity(item.cartItemId, item.quantity + 1)}>
                    +
                  </button>
                  <button class="delete-btn" onClick={() => this.deleteItem(item.cartItemId)}>
                    Delete
                  </button>
                  <button>Order</button>
                </div>
              </li>
            ))}
          </ul>
        </div>
      </div>
    );
  }
}
