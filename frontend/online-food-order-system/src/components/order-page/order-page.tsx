import { Component, State, Host, h } from '@stencil/core';

@Component({
  tag: 'order-page',
  styleUrl: 'order-page.css',
  shadow: true,
})
export class OrderPage {
  @State() orders: any[] = [];
  @State() loading: boolean = true;
  async componentWillLoad() {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('jwt');

    if (!userId || !token) {
      window.location.href = '/login';
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/users/${userId}/orders`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (!response.ok) throw new Error(`HTTP error ${response.status}`);

      const data = await response.json();
      this.orders = data; // ✅ Correct
    } catch (error) {
      console.error('Failed to load orders:', error);
    } finally {
      this.loading = false;
    }
  }

  render() {
    if (this.loading) {
      return <p>Loading your cart...</p>;
    }

    if (this.orders.length === 0) {
      return (
        <div class="main">
          <div class="navbar">
            <app-navbar></app-navbar>
          </div>
          <div class="cart">
            <h1>Your orders</h1>
            <p>no Orders Yet!!!.</p>
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
          <h1>Your Ordered Items</h1>
          <ul>
            {this.orders.map(order => (
              <li class="order-item">
                <h2>Order #{order.orderId}</h2>
                <p>Status: {order.orderStatus}</p>
                <p>Total: ₹{order.totalAmount}</p>
                <p>Restaurant: {order.restaurant.name}</p>
                <p>Placed: {order.createdAt}</p>
              </li>
            ))}
          </ul>
        </div>
      </div>
    );
  }
}
