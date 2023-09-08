import { WebPlugin } from '@capacitor/core';
import type { SystemInfoPlugin, SystemInformations } from './definitions';
export declare class SystemInfoWeb extends WebPlugin implements SystemInfoPlugin {
    start(): Promise<SystemInformations | undefined>;
    stop(): Promise<void>;
}
