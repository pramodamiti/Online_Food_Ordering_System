import { Component, State, h, Event, EventEmitter } from '@stencil/core';

@Component({
  tag: 'login-page',
  styleUrl: 'login-page.css',
  shadow: true,
})
export class LoginPage {
  // Add these fields to your component class:
  private slideTimer: any;
  private images: string[] = [
    '/assets/images/loginbanner/banner1.png',
    '/assets/images/loginbanner/banner2.png',
    'assets/images/loginbanner/banner3.png',
    '/assets/images/loginbanner/banner4.png',
  ];

  @State() currentSlide: number = 0;

  // Lifecycle: start/stop autoplay
  componentDidLoad() {
    this.startAutoplay();
  }
  disconnectedCallback() {
    this.stopAutoplay();
  }

  private startAutoplay() {
    this.stopAutoplay();
    this.slideTimer = setInterval(() => {
      this.currentSlide = (this.currentSlide + 1) % this.images.length;
    }, 3500);
  }
  private stopAutoplay() {
    if (this.slideTimer) {
      clearInterval(this.slideTimer);
      this.slideTimer = undefined;
    }
  }
  @State() email: string = '';
  @State() password: string = '';
  @State() message: string = '';

  @Event() loginSuccess: EventEmitter<void>;

  async handleLogin(e: Event) {
    e.preventDefault();
    try {
      const res = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email: this.email, password: this.password }),
      });

      if (!res.ok) throw new Error('Invalid credentials');

      const token = await res.text();
      localStorage.setItem('jwt', token);
      this.message = 'Login successful!';
      setTimeout(() => {
        history.pushState({}, '', '/home');
        window.dispatchEvent(new PopStateEvent('popstate'));
      }, 800);
      this.loginSuccess.emit();
    } catch (err) {
      this.message = 'Login failed: ' + (err.message || err);
    }
  }

  render() {
    return (
      <div class="container">
        <div class="left" onMouseEnter={() => this.stopAutoplay()} onMouseLeave={() => this.startAutoplay()}>
          <div class="carousel" role="region" aria-label="Promotional images">
            <div class="slides" style={{ transform: `translateX(-${this.currentSlide * 100}%)` }}>
              {this.images.map((src, idx) => (
                <div class="slide" key={idx}>
                  <img src={src} alt={`Slide ${idx + 1}`} />
                </div>
              ))}
            </div>

            <div class="indicators" aria-hidden="true">
              {this.images.map((_, i) => (
                <span class={{ dot: true, active: i === this.currentSlide }} key={i} />
              ))}
            </div>
          </div>
        </div>

        <div class="right">
          <form onSubmit={e => this.handleLogin(e)}>
            <div class="login-title">Login</div>

            <input type="text" placeholder="Email" value={this.email} onInput={(e: any) => (this.email = e.target.value)} required />

            <input type="password" placeholder="Password" value={this.password} onInput={(e: any) => (this.password = e.target.value)} required />

            <button type="submit">Login</button>

            <div class="message">{this.message}</div>

            <div class="social-row" aria-hidden="false">
              <button type="button" class="social-btn" title="Login with Google" onClick={() => alert('Replace with Google OAuth')}>
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M21 12.25c0-.68-.06-1.33-.18-1.96H12v3.72h4.84c-.21 1.12-.86 2.07-1.83 2.7v2.24h2.96c1.73-1.6 2.72-3.95 2.72-6.7z" fill="#4285F4" />
                  <path d="M12 22c2.43 0 4.47-.8 5.96-2.18l-2.96-2.24c-.82.55-1.87.88-3 .88-2.31 0-4.27-1.56-4.97-3.66H3.96v2.31C5.45 19.93 8.52 22 12 22z" fill="#34A853" />
                  <path d="M7.03 13.8A5.38 5.38 0 0 1 6.5 12c0-.55.1-1.09.3-1.6V8.09H3.96A9.98 9.98 0 0 0 2 12c0 1.7.4 3.3 1.1 4.73l3.93-2.93z" fill="#FBBC05" />
                  <path d="M12 6.5c1.32 0 2.51.45 3.45 1.34l2.59-2.6C16.44 3.45 14.4 2.5 12 2.5 8.52 2.5 5.45 4.57 3.96 7.91l3.93 2.99C7.73 8.06 9.69 6.5 12 6.5z" fill="#EA4335" />
                </svg>
              </button>

              <button type="button" class="social-btn" title="Login with Facebook" onClick={() => alert('Replace with Facebook OAuth')}>
                <svg width="16" height="16" viewBox="0 0 24 24" fill="#1877F2" xmlns="http://www.w3.org/2000/svg">
                  <path d="M22 12C22 6.48 17.52 2 12 2S2 6.48 2 12c0 5 3.66 9.12 8.44 9.88v-6.99H7.9V12h2.54V9.8c0-2.5 1.49-3.89 3.78-3.89 1.1 0 2.25.2 2.25.2v2.47h-1.26c-1.24 0-1.62.77-1.62 1.56V12h2.77l-.44 2.89h-2.33v6.99C18.34 21.12 22 17 22 12z" />
                </svg>
              </button>
            </div>

            <div class="social-label">or sign in with</div>

            <div class="footer" style={{ marginTop: '8px' }}>
              Don't have an account?{' '}
              <a
                href="#"
                onClick={e => {
                  e.preventDefault();
                  history.pushState({}, '', '/signup');
                  window.dispatchEvent(new PopStateEvent('popstate'));
                }}
              >
                Sign up here
              </a>
            </div>
          </form>
        </div>
      </div>
    );
  }
}
