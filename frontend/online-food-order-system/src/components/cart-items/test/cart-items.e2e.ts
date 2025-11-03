import { newE2EPage } from '@stencil/core/testing';

describe('cart-items', () => {
  it('renders', async () => {
    const page = await newE2EPage();
    await page.setContent('<cart-items></cart-items>');

    const element = await page.find('cart-items');
    expect(element).toHaveClass('hydrated');
  });
});
