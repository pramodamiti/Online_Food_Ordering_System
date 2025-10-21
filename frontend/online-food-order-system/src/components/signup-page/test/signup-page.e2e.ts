import { newE2EPage } from '@stencil/core/testing';

describe('signup-page', () => {
  it('renders', async () => {
    const page = await newE2EPage();
    await page.setContent('<signup-page></signup-page>');

    const element = await page.find('signup-page');
    expect(element).toHaveClass('hydrated');
  });
});
