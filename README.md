# @danyalwe/capacitor-systeminfo

Get access to every info about the device software and hardware!

## Install

```bash
npm install @danyalwe/capacitor-systeminfo
npx cap sync
```

## API

<docgen-index>

* [`start()`](#start)
* [`stop()`](#stop)
* [`addListener('runTimeChange', ...)`](#addlistenerruntimechange)
* [`removeAllListeners()`](#removealllisteners)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### start()

```typescript
start() => Promise<SystemInformations | undefined>
```

**Returns:** <code>Promise&lt;<a href="#systeminformations">SystemInformations</a>&gt;</code>

--------------------


### stop()

```typescript
stop() => Promise<void>
```

--------------------


### addListener('runTimeChange', ...)

```typescript
addListener(eventName: 'runTimeChange', listenerFunc: (event: RunTimeInfos) => void) => Promise<PluginListenerHandle>
```

| Param              | Type                                                                      |
| ------------------ | ------------------------------------------------------------------------- |
| **`eventName`**    | <code>'runTimeChange'</code>                                              |
| **`listenerFunc`** | <code>(event: <a href="#runtimeinfos">RunTimeInfos</a>) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt;</code>

--------------------


### removeAllListeners()

```typescript
removeAllListeners() => Promise<void>
```

--------------------


### Interfaces


#### SoftWareInfos

| Prop             | Type                |
| ---------------- | ------------------- |
| **`sdkVersion`** | <code>number</code> |


#### HardWareInfos

| Prop      | Type                |
| --------- | ------------------- |
| **`cpu`** | <code>number</code> |


#### RunTimeInfos

| Prop      | Type                |
| --------- | ------------------- |
| **`ram`** | <code>number</code> |
| **`hdd`** | <code>number</code> |


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


### Type Aliases


#### SystemInformations

<code><a href="#softwareinfos">SoftWareInfos</a> & <a href="#hardwareinfos">HardWareInfos</a> & <a href="#runtimeinfos">RunTimeInfos</a></code>

</docgen-api>
