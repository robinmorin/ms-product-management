package org.test.capitole.unittests.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import java.util.AbstractMap;
import java.util.Map;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

/***
 * Checking Layer Architecture Test (Rules Arq.Hexa/Clean.Arq)
 */
@AnalyzeClasses(packages = "org.test.capitole")
public class LayerArchitectureTest {
    private static Map.Entry<String,String> API_LAYER_PACKAGES =  new AbstractMap.SimpleImmutableEntry<>("Application Layer","org.test.capitole.api..");
    private static Map.Entry<String,String> INFRASTRUTURE_LAYER_PACKAGE =  new AbstractMap.SimpleImmutableEntry<>("Infrastruture Layer","org.test.capitole.infrastructure..");
    private static Map.Entry<String,String> CORE_LAYER_PACKAGES =  new AbstractMap.SimpleImmutableEntry<>("Core Layer (Domain)","org.test.capitole.core..");
    private static Map.Entry<String,String> DATA_ADAPTER_LAYER_PACKAGES =  new AbstractMap.SimpleImmutableEntry<>("Data Adapter Layer (Adapter Out)","org.test.capitole.infrastructure.adapter.out.persistence..");


    @ArchTest
    static final ArchRule rules_layer_interacting = layeredArchitecture()
        .consideringOnlyDependenciesInLayers()
        .layer(API_LAYER_PACKAGES.getKey()).definedBy(API_LAYER_PACKAGES.getValue())
        .layer(INFRASTRUTURE_LAYER_PACKAGE.getKey()).definedBy(INFRASTRUTURE_LAYER_PACKAGE.getValue())
        .layer(CORE_LAYER_PACKAGES.getKey()).definedBy(CORE_LAYER_PACKAGES.getValue())
        .layer(DATA_ADAPTER_LAYER_PACKAGES.getKey()).definedBy(DATA_ADAPTER_LAYER_PACKAGES.getValue())
        .whereLayer(CORE_LAYER_PACKAGES.getKey()).mayNotAccessAnyLayer()
        .whereLayer(DATA_ADAPTER_LAYER_PACKAGES.getKey()).mayNotBeAccessedByAnyLayer()
        .whereLayer(INFRASTRUTURE_LAYER_PACKAGE.getKey()).mayNotBeAccessedByAnyLayer()
        .whereLayer(API_LAYER_PACKAGES.getKey()).mayOnlyBeAccessedByLayers(INFRASTRUTURE_LAYER_PACKAGE.getKey())
        .whereLayer(CORE_LAYER_PACKAGES.getKey()).mayOnlyBeAccessedByLayers(API_LAYER_PACKAGES.getKey(), DATA_ADAPTER_LAYER_PACKAGES.getKey(), INFRASTRUTURE_LAYER_PACKAGE.getKey());
}