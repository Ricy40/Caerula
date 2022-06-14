# mode_switcher=0 -> sphere
# mode_switcher=1 -> cube
# mode is changed from expansion/functions/items/rocket_wrench

tp @s[scores={mode_switcher=0}] ~ ~ ~ ~7 ~

execute if score @s mode_switcher matches 0 positioned ~ ~0.5 ~ run function expansion:blocks/terraformer/particles/terraformer_sphere_particle
execute if score @s mode_switcher matches 1 positioned ~ ~0.5 ~ run function expansion:blocks/terraformer/particles/terraformer_cube_particle

scoreboard players add @s terraform 1

execute if block ~ ~-1 ~ minecraft:redstone_block if score @p expansion_dim matches 5 run function expansion:blocks/terraformer/terraformers/mars_terraformer
execute if block ~ ~-1 ~ minecraft:redstone_block if score @p expansion_dim matches 4 run function expansion:blocks/terraformer/terraformers/moon_terraformer

scoreboard players set @s[scores={terraform=10..}] terraform 0