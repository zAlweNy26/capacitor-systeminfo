var capacitorSystemInfo = (function (exports, core, platform) {
    'use strict';

    const SystemInfo = core.registerPlugin('SystemInfo', {
        web: () => Promise.resolve().then(function () { return web; }).then((m) => new m.SystemInfoWeb()),
    });

    class SystemInfoWeb extends core.WebPlugin {
        async getInfos() {
            var _a, _b, _c, _d, _e, _f, _g;
            const features = [];
            if ('NDEFReader' in window)
                features.push('nfc');
            if ('bluetooth' in navigator)
                features.push('bluetooth');
            if ('geolocation' in navigator)
                features.push('gps');
            if (navigator.maxTouchPoints > 0)
                features.push('touchscreen');
            const devices = await navigator.mediaDevices.enumerateDevices();
            if (devices.some((d) => d.kind === 'videoinput'))
                features.push('camera');
            if (devices.some((d) => d.kind === 'audiooutput'))
                features.push('speaker');
            if (devices.some((d) => d.kind === 'audioinput'))
                features.push('microphone');
            const totalRAM = 'deviceMemory' in navigator ? navigator.deviceMemory : 0;
            const estimate = 'storage' in navigator ? (_a = (await navigator.storage.estimate()).quota) !== null && _a !== void 0 ? _a : 0 : 0;
            // Not real total, it's the available
            const totalHDD = parseInt((estimate / 1024 ** 3).toFixed(2));
            return {
                osName: (_c = (_b = platform.os) === null || _b === void 0 ? void 0 : _b.family) !== null && _c !== void 0 ? _c : 'Unknown',
                osVersion: (_e = (_d = platform.os) === null || _d === void 0 ? void 0 : _d.version) !== null && _e !== void 0 ? _e : 'Unknown',
                brandName: (_f = platform.product) !== null && _f !== void 0 ? _f : 'Unknown',
                manufacturer: (_g = platform.manufacturer) !== null && _g !== void 0 ? _g : 'Unknown',
                totalRAM,
                totalHDD,
                totalCores: 'hardwareConcurrency' in navigator ? navigator.hardwareConcurrency : 1,
                features,
            };
        }
        async start() {
            this.unavailable('Listener not available in browser environment');
        }
        async stop() {
            this.unavailable('Listener not available in browser environment');
        }
        addListener(eventName, listenerFunc) {
            this.unavailable('Listener not available in browser environment');
            return super.addListener(eventName, listenerFunc);
        }
    }

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        SystemInfoWeb: SystemInfoWeb
    });

    exports.SystemInfo = SystemInfo;

    return exports;

})({}, capacitorExports, platform);
//# sourceMappingURL=plugin.js.map
