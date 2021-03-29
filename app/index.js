"use strict";

const Generator = require("yeoman-generator");
const mkdirp = require("mkdirp");
const yosay = require("yosay");
const chalk = require("chalk");

module.exports = class extends Generator {
  constructor(args, opts) {
    super(args, opts);
    this.props = {};
  }

  async prompting() {
    this.log(
      yosay("Welcome to " + chalk.green("Android MVVM") + " generator!")
    );

    const prompts = [
      {
        name: "name",
        message: "What you wanna name your app?",
        store: true,
        default: this.appname, // Default to current folder name
      },
      {
        name: "package",
        message:
          "What package will you be publishing the app under?(eg.: com.example.name)",
        default: "com.example.testapp",
      },
      {
        type: "list",
        name: "language",
        message: "What language would you like to use? ",
        choices: [
          {
            value: "java",
            name: "Java",
          },
        ],
        default: 0,
      },
      {
        name: "targetSdk",
        message: "What Android SDK will you be targeting?",
        store: true,
        default: 26, // Android 8.0 (O(7.1+))
      },
      {
        name: "minSdk",
        message: "What is the minimum Android SDK you wish to support?",
        store: true,
        default: 19, // Android 4.0 (Ice Cream Sandwich)
      },
    ];

    return await this.prompt(prompts).then((props) => {
      this.props.appPackage = props.package;
      this.props.language = props.language;
      this.appName = props.name;
      this.appPackage = props.package;
      this.androidTargetSdkVersion = props.targetSdk;
      this.androidMinSdkVersion = props.minSdk;
    });
  }

  writing() {
    var packageDir = this.props.appPackage.replace(/\./g, "/");

    var appFolder;
    if (this.props.language == "java") {
      appFolder = "java";
    }

    mkdirp("app");
    mkdirp("app/src/main/assets");
    mkdirp("app/src/main/java/" + packageDir);
    mkdirp("app/src/androidTest/java/" + packageDir);
    mkdirp("app/src/test/java/" + packageDir);
    var appPath =
      "C:/Users/dell/Desktop/generator-mvvm-minks/generator-android-minks/app/template" +
      "/" +
      appFolder +
      "/";

    // this.fs.copy(appPath + ".gitignore", ".gitignore");
    this.fs.copy(appPath + "build.gradle", "build.gradle");
    this.fs.copy(appPath + "gradle.properties", "gradle.properties");
    this.fs.copy(appPath + "gradlew", "gradlew");
    this.fs.copy(appPath + "gradlew.bat", "gradlew.bat");
    this.fs.copy(appPath + "settings.gradle", "settings.gradle");
    // this.fs.copy(appPath + "app/.gitignore", "app/.gitignore");
    this.fs.copy(appPath + "app/proguard-rules.pro", "app/proguard-rules.pro");
    this.fs.copy(appPath + "gradle", "gradle");
    this.fs.copy(appPath + "app/src/main/res", "app/src/main/res");
    // this.fs.copyTpl(appPath + "README.md", "README.md", this.props);
    this.fs.copyTpl(
      appPath + "app/build.gradle",
      "app/build.gradle",
      this.props
    );
    this.fs.copyTpl(
      appPath + "app/src/androidTest/java/com/minks/sample",
      "app/src/androidTest/java/" + packageDir,
      this.props
    );
    this.fs.copyTpl(
      appPath + "app/src/test/java/com/minks/sample",
      "app/src/test/java/" + packageDir,
      this.props
    );
    this.fs.copyTpl(
      appPath + "app/src/main/AndroidManifest.xml",
      "app/src/main/AndroidManifest.xml",
      this.props
    );
    this.fs.copyTpl(
      appPath + "app/src/main/java/com/minks/sample",
      "app/src/main/java/" + packageDir,
      this.props
    );
    this.fs.copyTpl(
      appPath + "app/src/main/res/layout",
      "app/src/main/res/layout",
      this.props
    );
  }
};
