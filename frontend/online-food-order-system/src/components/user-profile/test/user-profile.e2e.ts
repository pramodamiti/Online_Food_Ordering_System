import { newE2EPage } from '@stencil/core/testing';

describe('user-profile', () => {
  it('renders', async () => {
    const page = await newE2EPage();
    await page.setContent('<user-profile></user-profile>');

    const element = await page.find('user-profile');
    expect(element).toHaveClass('hydrated');
  });
});
