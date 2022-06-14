execute unless block ~ ~ ~ minecraft:dropper run function expansion:blocks/crafter/destroy
execute if entity @p[distance=..6] if block ~ ~ ~ minecraft:dropper[facing=up,triggered=false]{Items:[{}]} run function expansion:blocks/crafter/recipes
