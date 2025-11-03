import { Component, State, h } from '@stencil/core';

@Component({
  tag: 'user-profile',
  styleUrl: 'user-profile.css',
  shadow: true,
})
export class UserProfile {
  @State() user = {
    name: '',
    email: '',
    phoneNumber: '',
    address: '',
  };

  async componentWillLoad() {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('jwt');

    const res = await fetch(`http://localhost:8080/users/${userId}`, {
      headers: { Authorization: `Bearer ${token}` },
    });

    const data = await res.json();
    this.user = data;
  }

  handleInput(e, field) {
    this.user = { ...this.user, [field]: e.target.value };
  }

  async saveChanges() {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('jwt');

    await fetch(`http://localhost:8080/users/${userId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
      },
      body: JSON.stringify(this.user),
    });
  }

  render() {
    return (
      <div class="main">
        <div class="navbar">
          <app-navbar></app-navbar>
        </div>

        <div class="profile-container">
          <div class="profile-title">User Profile</div>

          <div class="profile-grid">
            <label>Name</label>
            <label>Email</label>
            <label>Phone Number</label>
            <label>Address</label>

            <input value={this.user.name} onInput={e => this.handleInput(e, 'name')} />
            <input value={this.user.email} onInput={e => this.handleInput(e, 'email')} />
            <input value={this.user.phoneNumber} onInput={e => this.handleInput(e, 'phoneNumber')} />
            <input value={this.user.address} onInput={e => this.handleInput(e, 'address')} />
          </div>

          <button class="save-btn" onClick={() => this.saveChanges()}>
            Save Changes
          </button>
        </div>
      </div>
    );
  }
}
