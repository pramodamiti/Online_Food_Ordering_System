import { newSpecPage } from '@stencil/core/testing';
import { HomeItems } from '../home-items';

describe('home-items', () => {
  it('renders', async () => {
    const page = await newSpecPage({
      components: [HomeItems],
      html: `<home-items></home-items>`,
    });
    expect(page.root).toEqualHtml(`
      <home-items>
        <mock:shadow-root>
          <slot></slot>
        </mock:shadow-root>
      </home-items>
    `);
  });
});
