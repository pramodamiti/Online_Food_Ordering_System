import { Component, State, h } from '@stencil/core';

@Component({
  tag: 'app-router',
  styleUrl: 'app-router.css',
  shadow: true,
})
export class AppRouter {
  @State() currentPath = window.location.pathname;

  componentWillLoad() {
    window.addEventListener('popstate', () => {
      this.currentPath = window.location.pathname;
    });
  }

  navigate(path: string) {
    history.pushState({}, '', path);
    this.currentPath = path;
  }

  render() {
    switch (this.currentPath) {
      case '/login':
        return <login-page />;
      case '/signup':
        return <signup-page />;
      case '/home':
        return <home-page />;
      default:
        return <home-page />;
    }
  }
}
