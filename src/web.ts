import { WebPlugin } from '@capacitor/core';
import platform from 'platform';

import { SystemInformations } from './definitions';
import type { Features, SystemInfoPlugin } from './definitions';

export class SystemInfoWeb extends WebPlugin implements SystemInfoPlugin {
  async getInfos(): Promise<SystemInformations> {
    const features: Features[] = [];

    if ('NDEFReader' in window) features.push('nfc');
    if ('bluetooth' in navigator) features.push('bluetooth');
    if ('geolocation' in navigator) features.push('gps');
    if (navigator.maxTouchPoints > 0) features.push('touchscreen');

    const devices = await navigator.mediaDevices.enumerateDevices();
    if (devices.some((d) => d.kind === 'videoinput')) features.push('camera');
    if (devices.some((d) => d.kind === 'audiooutput')) features.push('speaker');
    if (devices.some((d) => d.kind === 'audioinput')) features.push('microphone');

    const totalRAM = 'deviceMemory' in navigator ? (navigator.deviceMemory as number) : 0;

    const estimate = 'storage' in navigator ? (await navigator.storage.estimate()).quota ?? 0 : 0;
    // Not real total, it's the available
    const totalHDD = parseInt((estimate / 1024 ** 3).toFixed(2));

    return {
      osName: platform.os?.family ?? 'Unknown',
      osVersion: platform.os?.version ?? 'Unknown',
      brandName: platform.product ?? 'Unknown',
      manufacturer: platform.manufacturer ?? 'Unknown',
      totalRAM,
      totalHDD,
      totalCores: 'hardwareConcurrency' in navigator ? navigator.hardwareConcurrency : 1,
      features,
    } satisfies SystemInformations;
  }

  async start(): Promise<void> {
    throw this.unavailable('Listener not available in browser environment');
  }

  async stop(): Promise<void> {
    throw this.unavailable('Listener not available in browser environment');
  }
}
