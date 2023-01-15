# LauncherC

基于 AOSP [Launcher3](https://android.googlesource.com/platform/packages/apps/Launcher3) 开发，支持Android Studio / IDEA
编译。

Base on `android13-qpr1`,tag:`android-13.0.0_r24`

旨在构建一个可自定义 (Customizable)、向下兼容 (Compatible) 的 Launcher 应用。

如果您遇到与本地依赖相关的错误，请查看以下Shell脚本

```` shell
# 0.确保当前在LauncherC项目根目录
# 1.部分clone platform_frameworks_base
git clone https://github.com/liu-wanshun/platform_frameworks_base.git --depth=1 --no-checkout  --filter=blob:none
# 2.platform_frameworks_base 开启 sparseCheckout
git -C platform_frameworks_base config core.sparseCheckout true
# 3.将platform_frameworks_base 迁移至submodule
git submodule absorbgitdirs
# 4.复制sparse-checkout
cp -f platform_frameworks_base_sparse-checkout .git/modules/platform_frameworks_base/info/sparse-checkout
# 5.更新submodule
git submodule update --init -f 
````

## License

````
   Copyright 2023 liuwanshun

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
````