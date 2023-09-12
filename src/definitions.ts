import type { PluginListenerHandle } from '@capacitor/core';

export interface RuntimeInfos {
  usedRAM?: number;
  usedHDD?: number;
  usedSD?: number;
  //usedCPU?: number;
}

export interface SoftwareInfos {
  osName: string;
  osVersion: string;
  brandName: string;
  sdkVersion?: number;
  sdkName?: string;
  securityPatch?: string;
  uiVersion?: string;
  deviceID?: string;
  boardName?: string;
  bootloaderVersion?: string;
  supportedABIs?: string[];
}

export type Features =
  | 'bluetooth'
  | 'bluetoothLowEnergy'
  | 'microphone'
  | 'speaker'
  | 'nfc'
  | 'camera'
  | 'gamepad'
  | 'gps'
  | 'touchscreen'
  | 'wifi'
  | 'fingerprint'
  | 'face'
  | 'ethernet';

export interface HardwareInfos {
  modelID?: string;
  modelCodeName?: string;
  cpuModel?: string;
  cpuCores?: [number, number][];
  totalSD?: number;
  totalHDD: number;
  totalRAM: number;
  totalCores: number;
  manufacturer: string;
  features: Features[];
}

export type SystemInformations = SoftwareInfos & HardwareInfos;

export interface SystemInfoPlugin {
  getInfos(): Promise<SystemInformations>;
  start(): Promise<void>;
  stop(): Promise<void>;
  addListener(eventName: 'runtimeChange', listenerFunc: (event: RuntimeInfos) => void): Promise<PluginListenerHandle>;
  removeAllListeners(): Promise<void>;
}
