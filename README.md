# EDAlertSnackbar

1. Add the JitPack repository to your build file
```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

2. Add the dependency
```
dependencies {
    implementation 'com.github.EunidevStudio:EDAlertSnackbar:1.0.4'
}
```

Usage

```kotlin
// Type Success
EDAlertSnackbar.success(this@MainActivity, this, "Success Message", Snackbar.LENGTH_LONG).show()
				
// Type Warning
EDAlertSnackbar.warning(this@MainActivity, this, "Warning Message", Snackbar.LENGTH_LONG).show()
				
// Type Info
EDAlertSnackbar.info(this@MainActivity, this, "Info Message", Snackbar.LENGTH_LONG).show()
				
// Type Failed
EDAlertSnackbar.failed(this@MainActivity, this, "Failed Message", Snackbar.LENGTH_LONG).show()

EDAlertSnackbar.success(this@MainActivity, this, "Success", Snackbar.LENGTH_LONG).apply {
					
		// Add Callback
		addCallback(object : Snackbar.Callback() {
			override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
				Log.i("EDAlertSnackbar", "Dismissed")
			}
		
			override fun onShown(sb: Snackbar?) {
				Log.i("EDAlertSnackbar", "Shown")
			}
		})
    
		// Set action
		setAction("Oke") { Toast.makeText(this@MainActivity, "Oke", Toast.LENGTH_SHORT).show() }
		
		// Enable or Disable Icon
		iconEnabled(true)
		
		// Set Your Custom Alert Icon
		setAlertIcon(R.drawable.ic_android)
		
		// Set Action Text Color
		setActionTextColor(Color.WHITE)
		
		// Set Message Text Color
		setMessageTextColor(Color.WHITE)
		
		// Show Snackbar
		show()
}
```

