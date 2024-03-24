import type { PluginListenerHandle } from '@capacitor/core';

/** Interface for runtime information. */
export interface RuntimeInfos {
  /** The amount of used RAM in bytes. */
  usedRAM?: number;
  /** The amount of used HDD in bytes. */
  usedHDD?: number;
  /** The amount of used SD card storage in bytes. */
  usedSD?: number;
  //usedCPU?: number;
}

/** Represents software information about a device. */
export interface SoftwareInfos {
  /** The name of the operating system. */
  osName: string;
  /** The version of the operating system. */
  osVersion: string;
  /** The name of the device brand. */
  brandName: string;
  /** The version of the SDK (if applicable). */
  sdkVersion?: number;
  /** The name of the SDK (if applicable). */
  sdkName?: string;
  /** The security patch level (if applicable). */
  securityPatch?: string;
  /** The version of the UI (if applicable). */
  uiVersion?: string;
  /** The unique identifier of the device (if applicable). */
  deviceID?: string;
  /** The name of the device board (if applicable). */
  boardName?: string;
  /** The version of the bootloader (if applicable). */
  bootloaderVersion?: string;
  /** The list of supported ABIs (if applicable). */
  supportedABIs?: string[];
}

/** Represents the available features that can be queried using the Capacitor System Info plugin. */
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

/**
 * Represents hardware information of a device.
 */
export interface HardwareInfos {
  /** The model ID of the device. */
  modelID?: string;
  /** The code name of the device model. */
  modelCodeName?: string;
  /** The model of the CPU. */
  cpuModel?: string;
  /** The number of cores and threads of the CPU. */
  cpuCores?: [number, number][];
  /** The total size of the SD card in bytes. */
  totalSD?: number;
  /** The total size of the HDD in bytes. */
  totalHDD: number;
  /** The total size of the RAM in bytes. */
  totalRAM: number;
  /** The total number of cores in the CPU. */
  totalCores: number;
  /** The manufacturer of the device. */
  manufacturer: string;
  /** The features supported by the device. */
  features: Features[];
}

/** Represents a collection of system information, including both software and hardware information. */
export type SystemInformations = SoftwareInfos & HardwareInfos;

/** Interface for the System Info plugin */
export interface SystemInfoPlugin {
  /**
   * Returns a Promise that resolves with an object containing system information.
   * @returns SystemInformations
   */
  getInfos(): Promise<SystemInformations>;
  /**
   * Starts listening for system information changes.
   */
  start(): Promise<void>;
  /**
   * Stops listening for system information changes.
   */
  stop(): Promise<void>;
  /**
   * Adds a listener for the 'runtimeChange' event.
   * @param eventName - The name of the event to listen for.
   * @param listenerFunc - The function to call when the event is triggered.
   * @returns PluginListenerHandle
   */
  addListener(eventName: 'runtimeChange', listenerFunc: (event: RuntimeInfos) => void): Promise<PluginListenerHandle>;
  /**
   * Removes all event listeners.
   */
  removeAllListeners(): Promise<void>;
}
