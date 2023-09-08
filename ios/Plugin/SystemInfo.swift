import Foundation

@objc public class SystemInfo: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
