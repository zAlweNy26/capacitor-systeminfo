import { registerPlugin } from '@capacitor/core';

import type { SystemInfoPlugin } from './definitions';

const SystemInfo = registerPlugin<SystemInfoPlugin>('SystemInfo', {
  web: () => import('./web').then(m => new m.SystemInfoWeb()),
});

export * from './definitions';
export { SystemInfo };
