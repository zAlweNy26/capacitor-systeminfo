import { WebPlugin } from '@capacitor/core';
import type { SystemInformations, SystemInfoPlugin } from './definitions';
export declare class SystemInfoWeb extends WebPlugin implements SystemInfoPlugin {
    getInfos(): Promise<SystemInformations>;
    start(): Promise<void>;
    stop(): Promise<void>;
}
