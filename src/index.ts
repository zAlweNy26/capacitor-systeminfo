import type { SystemInfoPlugin } from './definitions'

import { registerPlugin } from '@capacitor/core'

const SystemInfo = registerPlugin<SystemInfoPlugin>('SystemInfo', {
  web: () => import('./web').then(m => new m.SystemInfoWeb()),
})

export * from './definitions'
export { SystemInfo }
