import { newSpecPage } from '@stencil/core/testing';
import { AppRouter } from '../app-router';

describe('app-router', () => {
  it('renders', async () => {
    const page = await newSpecPage({
      components: [AppRouter],
      html: `<app-router></app-router>`,
    });
    expect(page.root).toEqualHtml(`
      <app-router>
        <mock:shadow-root>
          <slot></slot>
        </mock:shadow-root>
      </app-router>
    `);
  });
});
