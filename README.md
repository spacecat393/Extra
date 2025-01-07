# Build
<span style="font-size: large; ">

- Set up and build [Nali-Library](https://github.com/spacecat393/Nali-Library)
- Set up and build [Small-Library](https://github.com/spacecat393/Small-Library)
- Set up Extra [TemplateDevEnv](https://github.com/CleanroomMC/TemplateDevEnv)
- When writing Mixins on IntelliJ, it is advisable to use latest [MinecraftDev Fork for RetroFuturaGradle](https://github.com/eigenraven/MinecraftDev/releases)
>build.gradle
```groovy
dependencies
{
	//...

	implementation rfg.deobf(files('../Nali-Library/build/libs/nali.jar'))
	implementation rfg.deobf(files('../Small-Library/build/libs/small.jar'))
}
```
>gradle.properties
```properties
mod_version = 2.0.0
root_package = com.nali
mod_id = extra
mod_name = Extra

use_mixins = true

is_coremod = true
coremod_plugin_class_name = com.nali.${mod_id}.Core
```
</span>