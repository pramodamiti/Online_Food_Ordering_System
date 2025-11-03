import { newSpecPage } from '@stencil/core/testing';
import { OrderPage } from '../order-page';

describe('order-page', () => {
  it('renders', async () => {
    const page = await newSpecPage({
      components: [OrderPage],
      html: `<order-page></order-page>`,
    });
    expect(page.root).toEqualHtml(`
      <order-page>
        <mock:shadow-root>
          <slot></slot>
        </mock:shadow-root>
      </order-page>
    `);
  });
});
