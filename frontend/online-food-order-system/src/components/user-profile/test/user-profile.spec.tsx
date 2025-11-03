import { newSpecPage } from '@stencil/core/testing';
import { UserProfile } from '../user-profile';

describe('user-profile', () => {
  it('renders', async () => {
    const page = await newSpecPage({
      components: [UserProfile],
      html: `<user-profile></user-profile>`,
    });
    expect(page.root).toEqualHtml(`
      <user-profile>
        <mock:shadow-root>
          <slot></slot>
        </mock:shadow-root>
      </user-profile>
    `);
  });
});
