import { State, Component, h } from '@stencil/core';

@Component({
  tag: 'home-page',
  styleUrl: 'home-page.css',
  shadow: true,
})
export class HomePage {
  @State() loggedIn: boolean = false;

  handleLoginSuccess() {
    this.loggedIn = true;
  }
  render() {
    return (
      <div class="home-page">
        <div class="navbar">
          <app-navbar />
        </div>
        <div class="menu-items">
          <home-items></home-items>
        </div>
      </div>
    );
  }
}
