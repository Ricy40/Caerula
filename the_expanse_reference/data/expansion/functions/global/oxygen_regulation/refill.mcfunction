# aestethics
execute rotated ~ 0 run particle poof ^ ^1 ^-0.5 0.1 0.1 0.1 0 50
playsound block.lava.extinguish player @a ~ ~ ~

# scoreboard operation witchery
scoreboard players operation @s hold_value_alt = @s oxygen_max
scoreboard players operation @s hold_value_alt -= @s oxygen_level
execute if score @s hold_value_alt > @s oxygen_storage run scoreboard players operation @s hold_value_alt = @s oxygen_storage
scoreboard players operation @s oxygen_level += @s hold_value_alt
scoreboard players operation @s oxygen_storage -= @s hold_value_alt

# merge the scoreboard with the item nbt through the use of a data storage while calculating the percentage
execute store result storage expansion:oxygen_tank_lvl data.tank_lvl int 1 run scoreboard players get @s oxygen_storage
function expansion:global/oxygen_regulation/tank_percentage
item modify entity @s weapon.mainhand expansion:oxygen_tank/score