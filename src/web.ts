import { WebPlugin } from '@capacitor/core';

import type { SystemInfoPlugin, SystemInformations } from './definitions';

export class SystemInfoWeb extends WebPlugin implements SystemInfoPlugin {
  async start(): Promise<SystemInformations | undefined> {
    throw new Error('Method not implemented.');
  }
  async stop(): Promise<void> {
    throw new Error('Method not implemented.');
  }
}
