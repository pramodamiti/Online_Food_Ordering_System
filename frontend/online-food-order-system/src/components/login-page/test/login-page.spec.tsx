import { newSpecPage } from '@stencil/core/testing';
import { LoginPage } from '../login-page';

describe('login-page', () => {
  it('renders', async () => {
    const page = await newSpecPage({
      components: [LoginPage],
      html: `<login-page></login-page>`,
    });
    expect(page.root).toEqualHtml(`
      <login-page>
        <mock:shadow-root>
          <slot></slot>
        </mock:shadow-root>
      </login-page>
    `);
  });
});
