import { Component, State, h } from '@stencil/core';

@Component({
  tag: 'signup-page',
  styleUrl: 'signup-page.css',
  shadow: true,
})
export class SignupPage {
  @State() name: string = '';
  @State() email: string = '';
  @State() password: string = '';
  @State() phoneNumber: string = '';
  @State() role: string = 'CUSTOMER';
  @State() address: string = '';
  @State() message: string = '';

  async handleSignup(e: Event) {
    e.preventDefault();

    const payload = {
      name: this.name,
      email: this.email,
      password: this.password,
      phoneNumber: this.phoneNumber,
      role: this.role,
      address: this.address,
    };

    try {
      const res = await fetch('http://localhost:8080/auth/signup', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      if (!res.ok) throw new Error('Signup failed');

      this.message = 'Signup successful! Redirecting to login...';
      setTimeout(() => {
        history.pushState({}, '', '/login');
        window.dispatchEvent(new PopStateEvent('popstate'));
      }, 1000);
    } catch (err) {
      this.message = 'Error: ' + (err && (err as any).message ? (err as any).message : err);
    }
  }

  render() {
    return (
      <div class="signup-container">
        <div class="left" aria-hidden="true">
          <div class="logo">
            <span>food</span>
            <span style={{ color: '#ff7a2a' }}>.</span>
          </div>
        </div>

        <div class="right">
          <h2>Create Account</h2>

          <form onSubmit={e => this.handleSignup(e)}>
            <input type="text" placeholder="Name" value={this.name} onInput={(e: any) => (this.name = e.target.value)} required />

            <input type="email" placeholder="Email" value={this.email} onInput={(e: any) => (this.email = e.target.value)} required />

            <input type="password" placeholder="Password" value={this.password} onInput={(e: any) => (this.password = e.target.value)} required />

            <input type="text" placeholder="Phone Number" value={this.phoneNumber} onInput={(e: any) => (this.phoneNumber = e.target.value)} />

            <input type="text" placeholder="Address" value={this.address} onInput={(e: any) => (this.address = e.target.value)} />

            <select onInput={(e: any) => (this.role = e.target.value)}>
              <option value="CUSTOMER">Customer</option>
              <option value="OWNER">Owner</option>
              <option value="ADMIN">Admin</option>
            </select>

            <button type="submit">Sign Up</button>
          </form>

          <div class="message">{this.message}</div>

          <div class="footer">
            Already have an account?{' '}
            <a
              href="#"
              onClick={e => {
                e.preventDefault();
                history.pushState({}, '', '/login');
                window.dispatchEvent(new PopStateEvent('popstate'));
              }}
            >
              Log in
            </a>
          </div>
        </div>
      </div>
    );
  }
}
