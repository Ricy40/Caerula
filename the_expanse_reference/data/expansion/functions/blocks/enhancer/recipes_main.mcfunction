# copy modules
execute if block ~ ~ ~ minecraft:dropper[facing=up,triggered=false]{Items:[{Slot:2b,id:"minecraft:jigsaw",tag:{exp_module:1b},Count:1b},{Slot:0b,id:"minecraft:paper",Count:1b}]} run function expansion:blocks/enhancer/recipes/modules/copy_module

# equipment recipes(modules and colours)
execute if block ~ ~ ~ minecraft:dropper[facing=up,triggered=false]{Items:[{Slot:0b,id:"minecraft:carrot_on_a_stick",tag:{space_jetpack:1b},Count:1b}]} run function expansion:blocks/enhancer/equipment_recipes

# spaceship skins
execute if block ~ ~ ~ minecraft:dropper[facing=up,triggered=false]{Items:[{Slot:0b,id:"minecraft:carrot_on_a_stick",tag:{exp_spaceship:1b},Count:1b}]} run function expansion:blocks/enhancer/spaceship_recipes
