var capacitorSystemInfo = (function (exports, core) {
    'use strict';

    const SystemInfo = core.registerPlugin('SystemInfo', {
        web: () => Promise.resolve().then(function () { return web; }).then((m) => new m.SystemInfoWeb()),
    });

    class SystemInfoWeb extends core.WebPlugin {
        async start() {
            throw new Error('Method not implemented.');
        }
        async stop() {
            throw new Error('Method not implemented.');
        }
    }

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        SystemInfoWeb: SystemInfoWeb
    });

    exports.SystemInfo = SystemInfo;

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
