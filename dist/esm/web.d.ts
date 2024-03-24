import { WebPlugin } from '@capacitor/core';
import { SystemInformations } from './definitions';
import type { SystemInfoPlugin } from './definitions';
export declare class SystemInfoWeb extends WebPlugin implements SystemInfoPlugin {
    getInfos(): Promise<SystemInformations>;
    start(): Promise<void>;
    stop(): Promise<void>;
}
