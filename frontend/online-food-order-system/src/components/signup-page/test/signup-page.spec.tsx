import { newSpecPage } from '@stencil/core/testing';
import { SignupPage } from '../signup-page';

describe('signup-page', () => {
  it('renders', async () => {
    const page = await newSpecPage({
      components: [SignupPage],
      html: `<signup-page></signup-page>`,
    });
    expect(page.root).toEqualHtml(`
      <signup-page>
        <mock:shadow-root>
          <slot></slot>
        </mock:shadow-root>
      </signup-page>
    `);
  });
});
