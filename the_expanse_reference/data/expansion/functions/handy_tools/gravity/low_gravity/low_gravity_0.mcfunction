execute if score @s exp_jump matches 1.. unless entity @s[predicate=expansion:nbt_checks/armor/magnetic_boots] run effect give @s minecraft:levitation 1 0 true
execute unless block ~ ~2 ~ #expansion:expansion_air run effect clear @s minecraft:levitation
execute unless entity @s[predicate=expansion:nbt_checks/armor/magnetic_boots] run effect give @s minecraft:slow_falling 2 0 true
execute if entity @s[predicate=expansion:nbt_checks/armor/magnetic_boots] if block ~ ~-1 ~ #expansion:expansion_air if block ~ ~-2 ~ #expansion:expansion_air if block ~ ~-3 ~ #expansion:expansion_air run effect give @s minecraft:slow_falling 2 0 true
