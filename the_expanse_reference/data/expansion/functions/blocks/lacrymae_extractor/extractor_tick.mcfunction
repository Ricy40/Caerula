execute unless block ~ ~ ~ minecraft:dropper run function expansion:blocks/lacrymae_extractor/destroy
execute unless block ~ ~1 ~ minecraft:hopper if block ~ ~ ~ minecraft:dropper run function expansion:blocks/lacrymae_extractor/destroy
execute if block ~ ~2 ~ minecraft:crying_obsidian run function expansion:blocks/lacrymae_extractor/extract
execute if entity @p[distance=..6] run function expansion:blocks/lacrymae_extractor/gui
