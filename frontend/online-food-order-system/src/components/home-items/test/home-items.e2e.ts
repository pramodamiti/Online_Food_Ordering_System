import { newE2EPage } from '@stencil/core/testing';

describe('home-items', () => {
  it('renders', async () => {
    const page = await newE2EPage();
    await page.setContent('<home-items></home-items>');

    const element = await page.find('home-items');
    expect(element).toHaveClass('hydrated');
  });
});
