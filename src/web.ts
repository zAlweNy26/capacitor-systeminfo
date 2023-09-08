import { WebPlugin } from '@capacitor/core';

import type { SystemInfoPlugin } from './definitions';

export class SystemInfoWeb extends WebPlugin implements SystemInfoPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
