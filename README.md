# Capacitor Plugin - SystemInfo

Get access to every info about the device software and hardware!

Supported Android version: `21+`\
Supported iOS version: `Not supported`\
Supported Browsers: `Chromium-based`

## Install

```bash
npm install @danyalwe/capacitor-systeminfo
npx cap sync
```

## Todos

- [ ] Improve documentation, add JSDoc strings
- [ ] Add support for iOS
- [ ] Add CPU usage

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
| **`totalHDD`**      | ✅      | ❌  | ✅  |
| **`totalSD`**       | ✅      | ❌  | ❌  |
| **`modelID`**       | ✅      | ❌  | ❌  |
| **`modelCodeName`** | ✅      | ❌  | ❌  |
| **`cpuModel`**      | ✅      | ❌  | ❌  |
| **`cpuCores`**      | ✅      | ❌  | ❌  |

| RuntimeInfos  | Android | iOS | Web |
| :------------ | :------ | :-- | :-- |
| **`usedRAM`** | ✅      | ❌  | ❌  |
| **`usedHDD`** | ✅      | ❌  | ❌  |
| **`usedSD`**  | ✅      | ❌  | ❌  |

## API

<docgen-index>

* [`getInfos()`](#getinfos)
* [`start()`](#start)
* [`stop()`](#stop)
* [`addListener('runtimeChange', ...)`](#addlistenerruntimechange)
* [`removeAllListeners()`](#removealllisteners)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getInfos()

```typescript
getInfos() => Promise<SystemInformations>
```

**Returns:** <code>Promise&lt;<a href="#systeminformations">SystemInformations</a>&gt;</code>

--------------------


### start()

```typescript
start() => Promise<void>
```

--------------------


### stop()

```typescript
stop() => Promise<void>
```

--------------------


### addListener('runtimeChange', ...)

```typescript
addListener(eventName: 'runtimeChange', listenerFunc: (event: RuntimeInfos) => void) => Promise<PluginListenerHandle>
```

| Param              | Type                                                                      |
| ------------------ | ------------------------------------------------------------------------- |
| **`eventName`**    | <code>'runtimeChange'</code>                                              |
| **`listenerFunc`** | <code>(event: <a href="#runtimeinfos">RuntimeInfos</a>) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt;</code>

--------------------


### removeAllListeners()

```typescript
removeAllListeners() => Promise<void>
```

--------------------


### Interfaces


#### SoftwareInfos

| Prop                    | Type                  |
| ----------------------- | --------------------- |
| **`osName`**            | <code>string</code>   |
| **`osVersion`**         | <code>string</code>   |
| **`brandName`**         | <code>string</code>   |
| **`sdkVersion`**        | <code>number</code>   |
| **`sdkName`**           | <code>string</code>   |
| **`securityPatch`**     | <code>string</code>   |
| **`uiVersion`**         | <code>string</code>   |
| **`deviceID`**          | <code>string</code>   |
| **`boardName`**         | <code>string</code>   |
| **`bootloaderVersion`** | <code>string</code>   |
| **`supportedABIs`**     | <code>string[]</code> |


#### HardwareInfos

| Prop                | Type                            |
| ------------------- | ------------------------------- |
| **`modelID`**       | <code>string</code>             |
| **`modelCodeName`** | <code>string</code>             |
| **`cpuModel`**      | <code>string</code>             |
| **`cpuCores`**      | <code>[number, number][]</code> |
| **`totalSD`**       | <code>number</code>             |
| **`totalHDD`**      | <code>number</code>             |
| **`totalRAM`**      | <code>number</code>             |
| **`totalCores`**    | <code>number</code>             |
| **`manufacturer`**  | <code>string</code>             |
| **`features`**      | <code>Features[]</code>         |


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


#### RuntimeInfos

| Prop          | Type                |
| ------------- | ------------------- |
| **`usedRAM`** | <code>number</code> |
| **`usedHDD`** | <code>number</code> |
| **`usedSD`**  | <code>number</code> |


### Type Aliases


#### SystemInformations

<code><a href="#softwareinfos">SoftwareInfos</a> & <a href="#hardwareinfos">HardwareInfos</a></code>


#### Features

<code>'bluetooth' | 'bluetoothLowEnergy' | 'microphone' | 'speaker' | 'nfc' | 'camera' | 'gamepad' | 'gps' | 'touchscreen' | 'wifi' | 'fingerprint' | 'face' | 'ethernet'</code>

</docgen-api>
