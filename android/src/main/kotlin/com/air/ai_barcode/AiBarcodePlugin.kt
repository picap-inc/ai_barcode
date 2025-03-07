package com.air.ai_barcode

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** AiBarcodePlugin */
public class AiBarcodePlugin : FlutterPlugin, MethodCallHandler {
    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        val channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "ai_barcode")
        channel.setMethodCallHandler(AiBarcodePlugin());

        /*注册：自己定义PlatformView*/
        flutterPluginBinding.platformViewRegistry.registerViewFactory("view_type_id_scanner_view", AndroidScannerViewFactory(flutterPluginBinding.binaryMessenger));
        flutterPluginBinding.platformViewRegistry.registerViewFactory("view_type_id_creator_view", AndroidCreatorViewFactory(flutterPluginBinding.binaryMessenger));
    }
    

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when {
            call.method == "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
            call.method == "test" -> result.success("Android test")
            else -> result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }
}
