# Running UI tests for Android
***
# Requirements
* JDK (version 17)
* Maven
* Android Studio

# Precondition:

* Depending on environment service app.apk must be loaded to the device.
* Check relative documentation for API calls or manual setup.

* Local environment hosts appium server on port 4723

# VM Options , System Properties:
* -ea -Denv.name=  -Dtoken= -Dusername=

* env - environment variable, which is used to define where to run tests,
it can be "local" , "mobitru", "browserstack" , "saucelabs".
When local is set other properties are not used.
* token env var , differ for each environment
* username environment variable, used and given by browserstack and saucelabs
