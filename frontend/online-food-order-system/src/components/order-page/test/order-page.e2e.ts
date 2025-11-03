import { newE2EPage } from '@stencil/core/testing';

describe('order-page', () => {
  it('renders', async () => {
    const page = await newE2EPage();
    await page.setContent('<order-page></order-page>');

    const element = await page.find('order-page');
    expect(element).toHaveClass('hydrated');
  });
});
