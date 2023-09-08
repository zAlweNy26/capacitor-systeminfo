import { registerPlugin } from '@capacitor/core';
const SystemInfo = registerPlugin('SystemInfo', {
    web: () => import('./web').then((m) => new m.SystemInfoWeb()),
});
export * from './definitions';
export { SystemInfo };
//# sourceMappingURL=index.js.map