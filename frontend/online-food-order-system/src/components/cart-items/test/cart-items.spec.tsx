import { newSpecPage } from '@stencil/core/testing';
import { CartItems } from '../cart-items';

describe('cart-items', () => {
  it('renders', async () => {
    const page = await newSpecPage({
      components: [CartItems],
      html: `<cart-items></cart-items>`,
    });
    expect(page.root).toEqualHtml(`
      <cart-items>
        <mock:shadow-root>
          <slot></slot>
        </mock:shadow-root>
      </cart-items>
    `);
  });
});
