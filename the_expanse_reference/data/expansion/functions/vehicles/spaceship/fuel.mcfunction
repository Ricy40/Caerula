execute if entity @p[nbt={Inventory:[{id:"minecraft:potion",tag:{lacrymae:1b}}]}] run tag @p add has_lacrymae
execute if entity @p[tag=has_lacrymae] if score @s fuel_level < @s fuel_max run clear @p minecraft:potion{lacrymae:1b} 1
execute if entity @p[tag=has_lacrymae] run scoreboard players set @s fuel_level 256
execute if entity @p[tag=has_lacrymae] run title @p title {"text":"Fuel level at 100%","color":"green","bold":true}

title @p subtitle {"text":" "}
execute unless entity @p[tag=has_lacrymae] run title @p title {"text":"Get more Lacrymae","color":"red","bold":true}

tag @p remove has_lacrymae
scoreboard players enable @p spaceship_optns