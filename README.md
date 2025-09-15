# Capacitor Plugin - SystemInfo

Get access to every info about the device software and hardware!

Supported Android version: `23+`\
Supported iOS version: `Not supported`\
Supported Browsers: `Chromium-based`

## Install

```bash
npm install @danyalwe/capacitor-systeminfo
npx cap sync
```

## Todos

- [ ] Add support for iOS
- [ ] Add CPU usage

## Example

The following example demonstrates how to use the SystemInfo plugin to access the device's info:

```ts
import { SystemInfo } from '@danyalwe/capacitor-systeminfo'

// Get all system informations
const info = await SystemInfo.getInfos()

// Add a listener to receive used ram, hdd and sd every second
SystemInfo.addListener('runtimeChange', (data) => {
  console.log('Runtime data:', data)
})

// Be sure to start ALWAYS AFTER adding the listener
await SystemInfo.start()

// Stop the listener
await SystemInfo.stop()
```

## Supported methods

| Name               | Android | iOS | Web |
| :----------------- | :------ | :-- | :-- |
| getInfos           | ✅      | ❌  | ✅  |
| start              | ✅      | ❌  | ❌  |
| stop               | ✅      | ❌  | ❌  |
| addListener        | ✅      | ❌  | ❌  |
| removeAllListeners | ✅      | ❌  | ❌  |

## Supported properties

| SoftwareInfos           | Android | iOS | Web |
| :---------------------- | :------ | :-- | :-- |
| **`osName`**            | ✅      | ❌  | ✅  |
| **`osVersion`**         | ✅      | ❌  | ✅  |
| **`brandName`**         | ✅      | ❌  | ✅  |
| **`sdkVersion`**        | ✅      | ❌  | ❌  |
| **`sdkName`**           | ✅      | ❌  | ❌  |
| **`securityPatch`**     | ✅      | ❌  | ❌  |
| **`uiVersion`**         | ✅      | ❌  | ❌  |
| **`deviceID`**          | ✅      | ❌  | ❌  |
| **`boardName`**         | ✅      | ❌  | ❌  |
| **`bootloaderVersion`** | ✅      | ❌  | ❌  |
| **`supportedABIs`**     | ✅      | ❌  | ❌  |

| HardwareInfos       | Android | iOS | Web |
| :------------------ | :------ | :-- | :-- |
| **`manufacturer`**  | ✅      | ❌  | ✅  |
| **`features`**      | ✅      | ❌  | ✅  |
| **`totalCores`**    | ✅      | ❌  | ✅  |
| **`totalRAM`**      | ✅      | ❌  | ✅  |
| **`totalHDD`**      | ✅      | ❌  | ⚠️  |
| **`totalSD`**       | ✅      | ❌  | ❌  |
| **`modelID`**       | ✅      | ❌  | ❌  |
| **`modelCodeName`** | ✅      | ❌  | ❌  |
| **`cpuModel`**      | ✅      | ❌  | ❌  |
| **`cpuCores`**      | ✅      | ❌  | ❌  |

⚠️: Not real total, it's the available

| RuntimeInfos  | Android | iOS | Web |
| :------------ | :------ | :-- | :-- |
| **`usedRAM`** | ✅      | ❌  | ❌  |
| **`usedHDD`** | ✅      | ❌  | ❌  |
| **`usedSD`**  | ✅      | ❌  | ❌  |

## API

<docgen-index>

* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

Interface for the System Info plugin

### Interfaces

#### SoftwareInfos

Represents software information about a device.

| Prop                    | Type                | Description                                          |
| ----------------------- | ------------------- | ---------------------------------------------------- |
| **`osName`**            | <code>string</code> | The name of the operating system.                    |
| **`osVersion`**         | <code>string</code> | The version of the operating system.                 |
| **`brandName`**         | <code>string</code> | The name of the device brand.                        |
| **`sdkVersion`**        | <code>number</code> | The version of the SDK (if applicable).              |
| **`sdkName`**           | <code>string</code> | The name of the SDK (if applicable).                 |
| **`securityPatch`**     | <code>string</code> | The security patch level (if applicable).            |
| **`uiVersion`**         | <code>string</code> | The version of the UI (if applicable).               |
| **`deviceID`**          | <code>string</code> | The unique identifier of the device (if applicable). |
| **`boardName`**         | <code>string</code> | The name of the device board (if applicable).        |
| **`bootloaderVersion`** | <code>string</code> | The version of the bootloader (if applicable).       |
| **`supportedABIs`**     | <code>{}</code>     | The list of supported ABIs (if applicable).          |

#### HardwareInfos

Represents hardware information of a device.

| Prop                | Type                | Description                                 |
| ------------------- | ------------------- | ------------------------------------------- |
| **`modelID`**       | <code>string</code> | The model ID of the device.                 |
| **`modelCodeName`** | <code>string</code> | The code name of the device model.          |
| **`cpuModel`**      | <code>string</code> | The model of the CPU.                       |
| **`cpuCores`**      | <code>{}</code>     | The number of cores and threads of the CPU. |
| **`totalSD`**       | <code>number</code> | The total size of the SD card in bytes.     |
| **`totalHDD`**      | <code>number</code> | The total size of the HDD in bytes.         |
| **`totalRAM`**      | <code>number</code> | The total size of the RAM in bytes.         |
| **`totalCores`**    | <code>number</code> | The total number of cores in the CPU.       |
| **`manufacturer`**  | <code>string</code> | The manufacturer of the device.             |
| **`features`**      | <code>{}</code>     | The features supported by the device.       |

#### RuntimeInfos

Interface for runtime information.

| Prop          | Type                | Description                                  |
| ------------- | ------------------- | -------------------------------------------- |
| **`usedRAM`** | <code>number</code> | The amount of used RAM in bytes.             |
| **`usedHDD`** | <code>number</code> | The amount of used HDD in bytes.             |
| **`usedSD`**  | <code>number</code> | The amount of used SD card storage in bytes. |

#### PluginListenerHandle

| Prop         | Type                      |
| ------------ | ------------------------- |
| **`remove`** | <code>() =&gt; any</code> |

### Type Aliases

#### SystemInformations

Represents a collection of system information, including both software and hardware information.

<code><a href="#softwareinfos">SoftwareInfos</a> & <a href="#hardwareinfos">HardwareInfos</a></code>

#### Features

Represents the available features that can be queried using the Capacitor System Info plugin.

<code>'bluetooth' | 'bluetoothLowEnergy' | 'microphone' | 'speaker' | 'nfc' | 'camera' | 'gamepad' | 'gps' | 'touchscreen' | 'wifi' | 'fingerprint' | 'face' | 'ethernet'</code>

</docgen-api>
