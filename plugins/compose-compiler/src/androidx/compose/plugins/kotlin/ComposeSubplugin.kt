/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package androidx.compose.plugins.kotlin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.AbstractCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinGradleSubplugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

class ComposeGradlePlugin : Plugin<Project> {
    companion object {
        fun isEnabled(project: Project) = project.plugins.findPlugin(ComposeGradlePlugin::class.java) != null
    }

    override fun apply(project: Project) {
        // nothing here
    }
}

class ComposeKotlinGradleSubplugin : KotlinGradleSubplugin<AbstractCompile> {
    companion object {
        const val COMPOSE_GROUP_NAME = "org.jetbrains.kotlin"
        const val COMPOSE_ARTIFACT_NAME = "compose-compiler-plugin-ij193"
    }

    override fun isApplicable(project: Project, task: AbstractCompile): Boolean =
        ComposeGradlePlugin.isEnabled(project)

    override fun apply(
        project: Project,
        kotlinCompile: AbstractCompile,
        javaCompile: AbstractCompile?,
        variantData: Any?,
        androidProjectHandler: Any?,
        kotlinCompilation: KotlinCompilation<*>?
    ): List<SubpluginOption> {
        return emptyList()
    }

    override fun getPluginArtifact(): SubpluginArtifact =
        SubpluginArtifact(COMPOSE_GROUP_NAME, COMPOSE_ARTIFACT_NAME)

    override fun getCompilerPluginId() = "org.jetbrains.compose.plugin"
}
