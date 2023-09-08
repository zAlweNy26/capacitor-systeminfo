import type { PluginListenerHandle } from '@capacitor/core';

export interface RunTimeInfos {
  ram: number
  hdd: number
}

export interface SoftWareInfos {
  sdkVersion: number
}

export interface HardWareInfos {
  cpu: number
}

export type SystemInformations = SoftWareInfos & HardWareInfos & RunTimeInfos

export interface SystemInfoPlugin {
  start(): Promise<SystemInformations | undefined>;
  stop(): Promise<void>;
  addListener(eventName: 'runTimeChange', listenerFunc: (event: RunTimeInfos) => void): Promise<PluginListenerHandle>;
  removeAllListeners(): Promise<void>;
}