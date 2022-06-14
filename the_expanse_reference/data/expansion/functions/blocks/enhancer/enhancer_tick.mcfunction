execute unless block ~ ~ ~ minecraft:dropper run function expansion:blocks/enhancer/destroy
execute if entity @p[distance=..6] if block ~ ~ ~ minecraft:dropper[facing=up,triggered=false]{Items:[{Slot:0b},{Slot:2b}]} unless block ~ ~ ~ minecraft:dropper[facing=up,triggered=false]{Items:[{Slot:7b}]} run function expansion:blocks/enhancer/recipes_main
execute if entity @p[distance=..6] run function expansion:blocks/enhancer/gui
