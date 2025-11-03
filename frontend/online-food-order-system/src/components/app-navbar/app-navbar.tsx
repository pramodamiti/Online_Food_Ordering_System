import { Component, h, State } from '@stencil/core';

@Component({
  tag: 'app-navbar',
  styleUrl: 'app-navbar.css',
  shadow: true,
})
export class AppNavbar {
  @State() loggedIn: boolean = !!localStorage.getItem('jwt');

  handleLogout() {
    localStorage.removeItem('jwt');
    this.loggedIn = false;
    history.pushState({}, '', '/login');
    window.dispatchEvent(new PopStateEvent('popstate'));
  }

  render() {
    return (
      <nav class="navbar">
        <div class="logo">
          <h2 style={{ fontFamily: 'Dancing Script, cursive', fontStyle: 'italic' }}>TastyBud</h2>
        </div>
        <div class="nav-links">
          {' '}
          <a href="/home">Home</a>
        </div>

        <div class="search-bar">
          <input type="text" placeholder="Search for restaurants or dishes..." />
          <button>Search</button>
        </div>

        <div class="nav-links">
          {this.loggedIn && <a href="/order">Orders</a>}
          {this.loggedIn && <a href="/cart">Cart</a>}
          {!this.loggedIn && <a href="/login">Login</a>}
          {!this.loggedIn && <a href="/signup">Signup</a>}
          {this.loggedIn && <a href="/profile">Profile</a>}
          {this.loggedIn && <button onClick={() => this.handleLogout()}>Logout</button>}
        </div>
      </nav>
    );
  }
}
