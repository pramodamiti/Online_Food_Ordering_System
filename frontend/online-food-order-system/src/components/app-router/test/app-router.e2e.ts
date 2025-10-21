import { newE2EPage } from '@stencil/core/testing';

describe('app-router', () => {
  it('renders', async () => {
    const page = await newE2EPage();
    await page.setContent('<app-router></app-router>');

    const element = await page.find('app-router');
    expect(element).toHaveClass('hydrated');
  });
});
